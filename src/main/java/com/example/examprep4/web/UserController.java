package com.example.examprep4.web;

import com.example.examprep4.model.binding.UserLoginBindingModel;
import com.example.examprep4.model.binding.UserRegisterBindingModel;
import com.example.examprep4.model.entity.User;
import com.example.examprep4.model.entity.enums.StyleName;
import com.example.examprep4.model.service.UserServiceModel;
import com.example.examprep4.repository.UserRepository;
import com.example.examprep4.service.SongService;
import com.example.examprep4.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
//@RequestMapping("")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final SongService songService;

    private UserServiceModel userServiceModel;

    public UserController(UserService userService, ModelMapper modelMapper,
                          UserRepository userRepository, SongService songService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.songService = songService;
    }

    @GetMapping("/users/register")
    public String register(Model model){
        if(!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }
    @PostMapping("/users/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }
        userService.register(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/users/login")
    public String login(Model model){
        if(!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            model.addAttribute("notFound", false);
        }
        return "login";
    }

    @PostMapping("/users/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               HttpSession httpSession){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);

            return "redirect:login";
        }
        userServiceModel = userService.findByUsernameAndPassword(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());

            if(userServiceModel == null){
                redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
                redirectAttributes.addFlashAttribute("notFound", true);


                return "redirect:login";
            }

            httpSession.setAttribute("user", userServiceModel);

        return "redirect:/";
    }
    @GetMapping("/users/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "index";
    }
    @GetMapping("home/add/{id}")
    public String addSongToCurrentUserPlaylist(@PathVariable String id){

        songService.addSongToCurrentUserPlayList(id, modelMapper.map(userServiceModel, User.class));
        return "redirect:/";
    }
    @GetMapping("/home/remove-all")
    public String removeAll(){
        songService.removeCurrentUserSongs(modelMapper.map(userServiceModel, User.class));
        return "redirect:/";
    }

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model){
        if(httpSession.getAttribute("user") == null){
            return "index";
        }
        model.addAttribute("pop", songService.findAllSongsByStyleName(StyleName.POP));
        model.addAttribute("rock", songService.findAllSongsByStyleName(StyleName.ROCK));
        model.addAttribute("jazz", songService.findAllSongsByStyleName(StyleName.JAZZ));

        model.addAttribute("playlist", userService.getUsersPlayList(userServiceModel));

        return "home";
    }


}
