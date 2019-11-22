package com.iquestgroup.l2c.event;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class DeviceEvent {
    private String eventId;
    private Long deviceId;
    private ZonedDateTime timestamp;
    private String details;
    private DeviceEventType deviceEventType;
    private SourceType sourceType;
}
