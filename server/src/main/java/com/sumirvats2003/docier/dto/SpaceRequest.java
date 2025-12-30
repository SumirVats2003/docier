package com.sumirvats2003.docier.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class SpaceRequest {
  private String title;
  private UUID ownerId;
}
