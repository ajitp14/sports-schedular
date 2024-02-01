package ajit.pailwan.sports.calender.controller;

import ajit.pailwan.sports.calender.entity.ScheduleEvent;
import ajit.pailwan.sports.calender.repository.ScheduleEventRepository;
import ajit.pailwan.sports.calender.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        if(eventEndDateTime.isBefore(eventStartDateTime)){
            return "redirect:/event-form?error=duplicate";
        }
        scheduleEvent.setEventEndDateTime(eventEndDateTime);
        scheduleEventRepository.save(scheduleEvent);
        return "redirect:/event-details";
    }

    @GetMapping("/event-details")
    public String showEventDetails(@RequestParam(defaultValue = "0") int page,Model model) {
        int pageSize = 10;
        // Retrieve events sorted by start date time in ascending order and paginated
        Page<ScheduleEvent> eventPage = scheduleEventRepository.findAllByOrderByEventStartDateTimeAsc(PageRequest.of(page, pageSize));
        List<ScheduleEvent> events = eventPage.getContent();
        model.addAttribute("events", events);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventPage.getTotalPages());

        return "event-details";
    }

    @PostMapping("/delete-event-details")
    public String deleteEventDetails() {
        List<ScheduleEvent> allEvents = scheduleEventRepository.findAll();
        List<ScheduleEvent> pastEvents = allEvents.stream().filter(scheduleEvent -> scheduleEvent.getEventEndDateTime().isBefore(LocalDateTime.now())).
                collect(Collectors.toList());
        scheduleEventRepository.deleteAll(pastEvents);
        return "redirect:/event-details";
    }
}
