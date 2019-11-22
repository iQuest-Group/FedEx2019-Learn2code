package com.iquestgroup.l2c.model;

import com.iquestgroup.l2c.event.SourceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO for the Device entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {

  private long id;

  private String brand;

  private String model;

  private String firmwareVersion;

  private SourceType sourceType;
}
