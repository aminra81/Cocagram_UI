package ir.sharif.aminra.listeners.timelinePage;

import ir.sharif.aminra.controller.timelinePage.TimelineController;
import ir.sharif.aminra.view.timelinePage.TimelineFXController;

public class TimelineListener {
    TimelineFXController timelineFXController;

    public TimelineListener(TimelineFXController timelineFXController) {
        this.timelineFXController = timelineFXController;
    }

    public void stringEventOccurred(String event) {
        TimelineController timelineController = new TimelineController();
        if(event.equals("refresh")) {
            timelineController.refresh(timelineFXController);
        }
    }
}
