package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloControllerTestSuite {

    @InjectMocks
    private TrelloController trelloController;
    @Mock
    private TrelloFacade trelloFacade;

    @Test
    void testGetTrelloBoardsWithLists() {
        // Given
        List<TrelloListDto> lists = List.of(new TrelloListDto("1","ToDo",false));
        List<TrelloBoardDto> trelloBoardDtos = List.of(new TrelloBoardDto("1","BoardName",lists));
        when(trelloFacade.fetchTrelloBoard()).thenReturn(trelloBoardDtos);
        // When
        List<TrelloBoardDto> boards = trelloController.getTrelloBoardsWithLists();
        // Then
        assertEquals(1, boards.size());
        assertThat(boards.get(0)).isNotNull();
        boards.forEach(board -> {
            assertThat(board.getId()).isEqualTo("1");
            assertThat(board.getName()).isEqualTo("BoardName");
            board.getLists().forEach(list -> {
                assertThat(list.getId()).isEqualTo("1");
                assertThat(list.getName()).isEqualTo("ToDo");
                assertThat(list.isClosed()).isEqualTo(false);
            });
        });
    }

    @Test
    void testCreatedTrelloCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("trelloCard","desc","top","1");
        TrelloBoardAndCard trelloBoardAndCard = new TrelloBoardAndCard(1,1);
        TrelloAttachementByType trelloAttachementByType = new TrelloAttachementByType(trelloBoardAndCard);
        TrelloBadges trelloBadges = new TrelloBadges(1, trelloAttachementByType);
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1","createdTrello","https://test.com", trelloBadges);
        when(trelloFacade.createdCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        // When
        CreatedTrelloCardDto createdTrelloCardDb = trelloController.createdTrelloCard(trelloCardDto);
        // Then
        assertThat(createdTrelloCardDb).isNotNull();
        assertEquals("1",createdTrelloCardDb.getId());
        assertEquals("createdTrello", createdTrelloCardDb.getName());
        assertEquals("https://test.com",createdTrelloCardDb.getShortUrl());
        assertThat(createdTrelloCardDb.getBadges()).isNotNull();
        assertThat(createdTrelloCardDb.getBadges().getAttachements()).isNotNull();
    }
}
