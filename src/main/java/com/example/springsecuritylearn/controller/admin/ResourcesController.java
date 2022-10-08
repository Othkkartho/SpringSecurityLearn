package com.example.springsecuritylearn.controller.admin;

import com.example.springsecuritylearn.domain.dto.ResourcesDto;
import com.example.springsecuritylearn.domain.entity.Resources;
import com.example.springsecuritylearn.domain.entity.Role;
import com.example.springsecuritylearn.repository.RoleRepository;
import com.example.springsecuritylearn.security.metadatasource.UrlFilterInvocationSecurityMetadatsSource;
import com.example.springsecuritylearn.service.MethodSecurityService;
import com.example.springsecuritylearn.service.ResourcesService;
import com.example.springsecuritylearn.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ResourcesController {
	private ResourcesService resourcesService;
	private RoleRepository roleRepository;
	private RoleService roleService;
	private MethodSecurityService methodSecurityService;
	private UrlFilterInvocationSecurityMetadatsSource filterInvocationSecurityMetadataSource;

	@Autowired
	private void setResourceController(ResourcesService resourcesService, RoleRepository roleRepository, RoleService roleService, MethodSecurityService methodSecurityService,
									   UrlFilterInvocationSecurityMetadatsSource filterInvocationSecurityMetadataSource) {
		this.resourcesService = resourcesService;
		this.roleRepository = roleRepository;
		this.roleService = roleService;
		this.methodSecurityService = methodSecurityService;
		this.filterInvocationSecurityMetadataSource = filterInvocationSecurityMetadataSource;
	}

	@GetMapping(value="/admin/resources")
	public String getResources(Model model) {
		List<Resources> resources = resourcesService.getResources();
		model.addAttribute("resources", resources);

		return "admin/resource/list";
	}

	@PostMapping(value="/admin/resources")
	public String createResources(ResourcesDto resourcesDto) throws Exception {
		ModelMapper modelMapper = new ModelMapper();
		Role role = roleRepository.findByRoleName(resourcesDto.getRoleName());
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		Resources resources = modelMapper.map(resourcesDto, Resources.class);
		resources.setRoleSet(roles);

		resourcesService.createResources(resources);

		if("url".equals(resourcesDto.getResourceType())) {
			filterInvocationSecurityMetadataSource.reload();
		}
		else if("method".equals(resourcesDto.getResourceType())) {
			methodSecurityService.addMethodSecured(resourcesDto.getResourceName(),resourcesDto.getRoleName());
		}

		return "redirect:/admin/resources";
	}

	@GetMapping(value="/admin/resources/register")
	public String viewRoles(Model model) {
		List<Role> roleList = roleService.getRoles();
		model.addAttribute("roleList", roleList);

		ResourcesDto resources = new ResourcesDto();
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(new Role());
		resources.setRoleSet(roleSet);
		model.addAttribute("resources", resources);

		return "admin/resource/detail";
	}

	@GetMapping(value="/admin/resources/{id}")
	public String getResources(@PathVariable String id, Model model) {
		List<Role> roleList = roleService.getRoles();
		model.addAttribute("roleList", roleList);
		Resources resources = resourcesService.getResources(Long.parseLong(id));

		ModelMapper modelMapper = new ModelMapper();
		ResourcesDto resourcesDto = modelMapper.map(resources, ResourcesDto.class);
		model.addAttribute("resources", resourcesDto);

		return "admin/resource/detail";
	}

	@GetMapping(value="/admin/resources/delete/{id}")
	public String removeResources(@PathVariable String id, Model model) throws Exception {

		Resources resources = resourcesService.getResources(Long.parseLong(id));
		resourcesService.deleteResources(Long.parseLong(id));

		if("url".equals(resources.getResourceType())) {
			filterInvocationSecurityMetadataSource.reload();
		}
		else if("method".equals(resources.getResourceType())) {
			methodSecurityService.removeMethodSecured(resources.getResourceName());
		}

		return "redirect:/admin/resources";
	}
}
