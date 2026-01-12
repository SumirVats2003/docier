package com.sumirvats2003.docier.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class DocumentRequest {
  private String title;
  private String content;
  private UUID spaceId;
}
