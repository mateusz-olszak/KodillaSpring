package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrelloMapperTestSuite {

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    void testMapToTrelloBoardList() {
        // Given
        List<TrelloListDto> trelloListsDto = List.of(new TrelloListDto("1","ToDo",false));
        List<TrelloBoardDto> trelloBoardsDto = List.of(new TrelloBoardDto("1","Team1",trelloListsDto));
        when(trelloMapper.mapToList(anyList())).thenCallRealMethod();
        when(trelloMapper.mapToBoard(trelloBoardsDto)).thenCallRealMethod();
        // When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoard(trelloBoardsDto);
        // Then
        assertEquals(1,trelloBoards.size());
        assertEquals(1,trelloBoards.get(0).getLists().size());
        assertEquals(TrelloBoard.class,trelloBoards.get(0).getClass());
    }

    @Test
    void testMapToTrelloBoardDtoList() {
        // Given
        List<TrelloList> trelloLists = List.of(new TrelloList("1","ToDo",false));
        List<TrelloBoard> trelloBoards = List.of(new TrelloBoard("1","Team1",trelloLists));
        when(trelloMapper.mapToListDto(anyList())).thenCallRealMethod();
        when(trelloMapper.mapToBoardDtoList(trelloBoards)).thenCallRealMethod();
        // When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardDtoList(trelloBoards);
        // Then
        assertEquals(1,trelloBoards.size());
        assertEquals(1,trelloBoards.get(0).getLists().size());
        assertEquals(TrelloBoardDto.class,trelloBoardsDto.get(0).getClass());
    }

    @Test
    void testMapToTrelloList() {
        // Given
        List<TrelloListDto> trelloListsDto = List.of(new TrelloListDto("1","ToDo",false));
        when(trelloMapper.mapToList(trelloListsDto)).thenCallRealMethod();
        // When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);
        // Then
        assertEquals(1, trelloLists.size());
        assertEquals(TrelloList.class, trelloLists.get(0).getClass());
    }

    @Test
    void testMapToTrelloListDto() {
        // Given
        List<TrelloList> trelloLists = List.of(new TrelloList("1","ToDo",false));
        when(trelloMapper.mapToListDto(trelloLists)).thenCallRealMethod();
        // When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);
        // Then
        assertEquals(1, trelloListsDto.size());
        assertEquals(TrelloListDto.class, trelloListsDto.get(0).getClass());
    }

    @Test
    void testMapToTrelloCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name","desc","top","1");
        when(trelloMapper.mapToTrelloCard(trelloCardDto)).thenCallRealMethod();
        // When
        TrelloCard trelloCard = trelloMapper.mapToTrelloCard(trelloCardDto);
        // Then
        assertEquals("name", trelloCard.getName());
        assertEquals(TrelloCard.class, trelloCard.getClass());
    }

    @Test
    void testMapToTrelloCardDto() {
        // Given
        TrelloCard trelloCard = new TrelloCard("name","desc","top","1");
        when(trelloMapper.mapToTrelloCardDto(trelloCard)).thenCallRealMethod();
        // When
        TrelloCardDto trelloCardDto = trelloMapper.mapToTrelloCardDto(trelloCard);
        // Then
        assertEquals("name", trelloCardDto.getName());
        assertEquals(TrelloCardDto.class, trelloCardDto.getClass());
    }
}
