package ajit.pailwan.sports.calender.controller;

import ajit.pailwan.sports.calender.entity.Team;
import ajit.pailwan.sports.calender.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/team-form")
    public String showTeamForm(Team team) {
        return "team-form";
    }

    @PostMapping("/team-form")
    public String submitTeamForm(Team team) {
        try {
            teamRepository.save(team);
            return "redirect:/team-details";
        } catch (Exception e) {
            return "redirect:/team-form?error=duplicate";
        }
    }

    @GetMapping("/team-details")
    public String showTeamDetails(Model model) {
        model.addAttribute("teams", teamRepository.findAll());
        return "team-details";
    }
}
