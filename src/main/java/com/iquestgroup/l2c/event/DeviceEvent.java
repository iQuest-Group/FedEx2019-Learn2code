package com.iquestgroup.l2c.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceEvent {
    private String eventId;
    private Long deviceId;
    private ZonedDateTime timestamp;
    private String details;
    private DeviceEventType deviceEventType;
    private SourceType sourceType;
}
