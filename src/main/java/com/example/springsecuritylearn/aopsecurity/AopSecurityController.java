package com.example.springsecuritylearn.aopsecurity;

import com.example.springsecuritylearn.domain.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AopSecurityController {
    private AopMethodService aopMethodService;
    private AopPointcutService aopPointcutService;
    private AopLiveMethodService aopLiveMethodService;

    @Autowired
    private void setAopSecurityController(AopMethodService aopMethodService, AopPointcutService aopPointcutService, AopLiveMethodService aopLiveMethodService) {
        this.aopMethodService = aopMethodService;
        this.aopPointcutService = aopPointcutService;
        this.aopLiveMethodService = aopLiveMethodService;
    }

    @GetMapping("/preAuthorize")
    public String preAuthorize(AccountDto account, Model model, Principal principal) {
        model.addAttribute("method", "Success @PreAuthorize");

        return "aop/method";
    }

    @GetMapping("/methodSecured")
    public String methodSecured(Model model) {
        aopMethodService.methodSecured();
        model.addAttribute("method", "Success MethodSecured");

        return "aop/method";
    }

    @GetMapping("/pointcutSecured")
    public String pointcutSecured(Model model){
        aopPointcutService.notSecured();
        aopPointcutService.pointcutSecured();
        model.addAttribute("method", "Success PointcutSecured");

        return "aop/method";
    }

    @GetMapping("/liveMethodSecured")
    public String liveMethodSecured(Model model){
        aopLiveMethodService.liveMethodSecured();
        model.addAttribute("method", "Success LiveMethodSecured");

        return "aop/method";
    }
}
