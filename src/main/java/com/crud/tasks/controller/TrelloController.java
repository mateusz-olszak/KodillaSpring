package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TrelloController {

    private final TrelloFacade trelloFacade;

    @GetMapping("/boards")
    public List<TrelloBoardDto> getTrelloBoardsWithLists(){
        return trelloFacade.fetchTrelloBoard();
    }

    @PostMapping("/cards")
    public CreatedTrelloCardDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloFacade.createdCard(trelloCardDto);
    }
}
