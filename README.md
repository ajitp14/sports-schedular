This simple spring boot application has total 5 services. It can be used to create upcoming events calender for your favourite sports teams.

1) Team Form - To create entry for the team.
	Required field - team name
	contraint - should be unique name(can't make same entry twice)
2) Team details - Retrieves all the teams created.
3) Schedule form - To create event for the team.
   Required fields - team name, event name, sports, event start date, event end date.
4) Schedule details - Retrieves all the events created. All events will be sorted in ascending order. Upcoming event will be at the top.  
5) Delete Events - It checks event end date for all the events, it the date is before current date it deletes entry for that event.

**Team Form**
<img width="931" alt="image" src="https://github.com/ajitp14/sports-schedular/assets/108980419/51e0a04a-9364-4052-b664-6e5a79cd2a64">

**Team Details**
<img width="947" alt="image" src="https://github.com/ajitp14/sports-schedular/assets/108980419/3ce9fd51-b183-4b71-a3ec-08fee886cd6a">

**Schedule Form**
<img width="907" alt="image" src="https://github.com/ajitp14/sports-schedular/assets/108980419/c4b51fe5-791d-4833-85c8-a16150040780">

**Schedule Details**
<img width="923" alt="image" src="https://github.com/ajitp14/sports-schedular/assets/108980419/79196f9c-850f-4d76-9c6a-457208a635d0">
