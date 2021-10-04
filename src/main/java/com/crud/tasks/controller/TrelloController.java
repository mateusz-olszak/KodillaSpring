package com.crud.tasks.controller;

import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloClient trelloClient;


    @GetMapping("/getTrelloBoardsFiltered") // Task 22.2
    public void getTrelloBoards(){
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

//        trelloBoards.stream() Filtracja dla zadania 22.2/3
//                .filter(p -> !p.getId().isEmpty() && !p.getName().isEmpty())
//                .filter(p -> p.getName().toLowerCase().contains("kodilla"))
//                .forEach(p -> {
//                    System.out.println(p.getId() + " " + p.getName());
//                });
    }


    @GetMapping("/getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoardsWithLists(){
        return trelloClient.getTrelloBoards();
    }

    @PostMapping("/createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloClient.createNewCard(trelloCardDto);
    }
}
