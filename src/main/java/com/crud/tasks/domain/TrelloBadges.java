package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloBadges {

    @JsonProperty("votes")
    private final int votes;

    @JsonProperty("attachmentsByType")
    private final TrelloAttachementByType attachements;

}
