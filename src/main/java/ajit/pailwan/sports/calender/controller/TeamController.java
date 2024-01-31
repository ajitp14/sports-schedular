package ajit.pailwan.sports.calender.controller;

import ajit.pailwan.sports.calender.entity.Team;
import ajit.pailwan.sports.calender.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public String showTeamDetails(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 9;
        Page<Team> teamPage = teamRepository.findAll(PageRequest.of(page, pageSize));
        List<Team> teams = teamPage.getContent();
        model.addAttribute("teams", teams);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", teamPage.getTotalPages());
        return "team-details";
    }
}
