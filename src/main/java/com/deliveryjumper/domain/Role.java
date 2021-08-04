package com.deliveryjumper.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

  CUSTOMER("ROLE_CUSTOMER", "CUSTOMER"),
  HOST("ROLE_HOST", "HOST"),
  RIDER("ROLE_RIDER", "RIDER");

  private final String key;
  private final String tile;

//  Role(String key, String tile) {
//    this.key = key;
//    this.tile = tile;
//  }
}
