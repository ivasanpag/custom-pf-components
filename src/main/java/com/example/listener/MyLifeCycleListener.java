package com.example.listener;

import lombok.extern.slf4j.Slf4j;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

@Slf4j
public class MyLifeCycleListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent phaseEvent) {
        log.info("AFTER:" + phaseEvent.getPhaseId().toString());
    }

    @Override
    public void beforePhase(PhaseEvent phaseEvent) {
        log.info("BEFORE: " + phaseEvent.getPhaseId().toString());
        if(phaseEvent.getPhaseId().getOrdinal() == 6) {
            log.info("Render Response");
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
