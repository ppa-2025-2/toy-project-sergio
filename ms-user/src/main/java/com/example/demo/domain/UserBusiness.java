package com.example.demo.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Controller.DTO.NewUserDTO;
import com.example.demo.Controller.DTO.requests.CreateTicketRequest;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.Entity.Profile;
import com.example.demo.Repository.Entity.Role;
import com.example.demo.Repository.Entity.User;
import com.example.demo.domain.interfaces.ITicketService;

@Service
public class UserBusiness {

    private UserRepository userRepository;
    private RoleBusiness roleBusiness;
    private ITicketService ticketService;
    private WorkStationBusiness workStationBusiness;

    public UserBusiness(
            UserRepository userRepository,
            RoleBusiness roleBusiness,
            ITicketService ticketService,
            WorkStationBusiness workStationBusiness
    ) {
        this.userRepository = userRepository;
        this.roleBusiness = roleBusiness;
        this.ticketService = ticketService;
        this.workStationBusiness = workStationBusiness;
    }
    
    public void cadastrarUsuario(NewUserDTO newUser) {
        validaUsuarioNaoExistente(newUser);
        
        User user = montarUsuario(newUser);

        user = userRepository.save(user);
        
        enviarTicketsCriacaoUsuario(user, newUser.createdBy());
    }

    private User montarUsuario(NewUserDTO newUser){
        Set<Role> roles = roleBusiness.listaRolesPorNome(newUser.roles());

        User user =new User(newUser);
        Profile profile = new Profile(newUser);
        user.setRoles(roles);
        profile.setUser(user);
        user.setProfile(profile);

        return user;
    }

    private void enviarTicketsCriacaoUsuario(User user, Integer criadorId){
        agendarOnBoarding(user, criadorId);
        workStationBusiness.agendarAlocacao(user, criadorId);
    }

    private void agendarOnBoarding(User user, Integer criadorId){
        CreateTicketRequest ticketRequest = new CreateTicketRequest(
            criadorId, 
            user.getId(),
            List.of(criadorId), 
            String.format("Usuário: %s; ID: %d", user.getHandle(), user.getId()), 
            "Onboarding", 
            "Realizar onboard de novo usuário cadastrado", 
            "google meeting");
        
        ticketService.createTicket(ticketRequest);
    }

    public ResponseEntity<List<User>> listarUsuarios() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    private void validaUsuarioNaoExistente(NewUserDTO newUser){
        buscarPorEmail(newUser.email())
            .ifPresent(user -> {
                throw new IllegalArgumentException("Usuário com o email " + newUser.email() + " já existe");
            });

        buscarPorHandle(newUser.handle())
            .ifPresent(user -> {
                throw new IllegalArgumentException("Usuário com o nome " + newUser.handle() + " já existe");
            });
    }

    private Optional<User> buscarPorEmail(String email){
        return userRepository.findByEmail(email);
    }

    private Optional<User> buscarPorHandle(String handle){
        return userRepository.findByHandle(handle);
    }
}
