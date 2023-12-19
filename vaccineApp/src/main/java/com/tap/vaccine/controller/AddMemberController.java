package com.tap.vaccine.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value ="/editMembers/{id}")
	public String editMember(@PathVariable int id, Model model) {
		System.out.println("Edit Entity");
		
		MemberEntity entity = addMemberService.getEntityById(id);
			model.addAttribute("id", entity.getMemberId());
			model.addAttribute("name", entity.getMemberName());
			model.addAttribute("gender", entity.getGender());
			model.addAttribute("dob", entity.getDob());
			model.addAttribute("govtId", entity.getGovernamentID());
			model.addAttribute("idProof", entity.getIdProof());
			model.addAttribute("vaccineType", entity.getVaccineType());
			model.addAttribute("dose", entity.getDose());
		
		return "/WEB-INF/editMember.jsp";
	}
	
	@RequestMapping(value = "/updateDetails" , method = RequestMethod.POST)
	public String updateMemberDetails(@RequestParam int memberId,
			@RequestParam("memberName") String memberName,
			@RequestParam("gender") String gender,
			@RequestParam("dob") String dob,
			@RequestParam("governmentId") String governmentId,
			@RequestParam("idProof") long idProof,
			@RequestParam("vaccineType") String vaccineType,
			@RequestParam("dose") int dose,Model model) {
		
		System.out.println("Updating ");
		String userEmail = LoginController.userEmail;
		boolean memberDetails = addMemberService.updateMemberDetails(memberId,memberName, gender, dob, governmentId, idProof, vaccineType, dose,userEmail);
		System.out.println(memberName+" "+gender+" "+" "+dob+" "+governmentId+" "+idProof+" "+vaccineType+" "+dose);
		System.out.println(memberDetails);
		if(memberDetails) {
			model.addAttribute("updateMessage", "Details Successfully Updated");
			System.out.println("edit Success");
			return "forward:/WEB-INF/addMember.jsp";
		}
		else {
			model.addAttribute("addMessage", "Something Went Wrong While Updating");
			System.out.println("Edit not success");
		}
		
		return "forward:/WEB-INF/editMember.jsp";
	}
	
	@RequestMapping(value = "/deleteMember/{id}")
	public String deleteMember(HttpServletRequest request,@PathVariable int id,Model model) {
		
		boolean deleteMemberEntityById = addMemberService.deleteMemberEntityById(id);
		if(deleteMemberEntityById) {
			HttpSession session = request.getSession();
	        String email = (String) session.getAttribute("userEmail");
	        System.out.println(email);
			int count = addMemberService.getMemberCount(email);
			System.out.println("Count "+count);
			 addMemberService.decreaseMemberCount(email, count);
			model.addAttribute("deleteMessage", "Deleted Succussfull");
			System.out.println("Deleted Succussfull");
			
			return "redirect:/viewAlltButton";
		}
		else {
			model.addAttribute("deleteMessage", "Something Went Wrong while Deleting");
			System.out.println("Something Went Wrong while Deleting");
		}
		
		return "forward:/WEB-INF/addMember.jsp";
	}
}
