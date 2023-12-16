package com.tap.vaccine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tap.vaccine.entity.MemberEntity;
import com.tap.vaccine.service.AddMemberService;

@Controller
public class AddMemberController {

	private AddMemberService addMemberService;

	public AddMemberController() {
		System.out.println("Default " + this.getClass().getSimpleName());
	}

	@Autowired
	public AddMemberController(AddMemberService addMemberService) {
		this.addMemberService = addMemberService;
	}

	@RequestMapping(value = "/addMemberButton")
	public String goToAddMember(Model model) {
		return "/WEB-INF/addMember.jsp";
	}

	@RequestMapping(value = "/addMember", method = RequestMethod.POST)
	public String addMember(@RequestParam("memberName") String memberName, @RequestParam("gender") String gender,
			@RequestParam("dob") String dob, @RequestParam("governmentId") String governmentId,
			@RequestParam("idProof") long idProof, @RequestParam("vaccineType") String vaccineType,
			@RequestParam("dose") int dose, Model model) {
		String userEmail = LoginController.userEmail;
		System.out.println(userEmail);
		System.out.println("this is AddMember in controller");

		int count = addMemberService.getMemberCount(userEmail);
		
		if(count != 4) {
			boolean data = addMemberService.validateMemberData(memberName, gender, dob, governmentId, idProof, vaccineType,
					dose, userEmail);
			if (data) {
				model.addAttribute("addMessage", "Successfully Added ");
				addMemberService.updateMemberCount(userEmail, count);
				System.out.println("added successfull");
			} else {
				model.addAttribute("addMessage", "Failed to add a new member");
				System.out.println("failed to add");
			}
		}else {
			model.addAttribute("addMessage", "your limit has exceeded , NOTE : You can only add upto 4 members");
		}
		return "forward:/WEB-INF/addMember.jsp";
	}

	@RequestMapping(value = "/viewAlltButton")
	public String viewAllMembers(Model model) {
		List<MemberEntity> allMembers = addMemberService.getAllMembers(LoginController.userEmail);
		model.addAttribute("members", allMembers);

		return "forward:/WEB-INF/addMember.jsp";
	}
}
