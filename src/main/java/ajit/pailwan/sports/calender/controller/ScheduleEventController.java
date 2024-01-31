package ajit.pailwan.sports.calender.controller;

import ajit.pailwan.sports.calender.entity.ScheduleEvent;
import ajit.pailwan.sports.calender.repository.ScheduleEventRepository;
import ajit.pailwan.sports.calender.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ScheduleEventController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ScheduleEventRepository scheduleEventRepository;

    @GetMapping("/event-form")
    public String showEventForm(ScheduleEvent scheduleEvent, Model model) {
        model.addAttribute("teams", teamRepository.findAll());
        return "event-form";
    }

    @PostMapping("/event-form")
    public String submitEventForm(@ModelAttribute ScheduleEvent scheduleEvent,
                                  @RequestParam("eventStartDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventStartDateTime,
                                  @RequestParam("eventEndDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventEndDateTime) {
        scheduleEvent.setEventStartDateTime(eventStartDateTime);
        scheduleEvent.setEventEndDateTime(eventEndDateTime);
        scheduleEventRepository.save(scheduleEvent);
        return "redirect:/event-details";
    }

    @GetMapping("/event-details")
    public String showEventDetails(Model model) {
        List<ScheduleEvent> events = scheduleEventRepository.findAllByOrderByEventStartDateTimeAsc();
        model.addAttribute("events", scheduleEventRepository.findAll());
        return "event-details";
    }
}
