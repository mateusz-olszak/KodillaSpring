package com.crud.tasks.facade;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Mock
    private TrelloService trelloService;

    @Test
    void testFetchTrelloBoards_practicalUsage() {
        // Given
        List<TrelloBoardDto> trelloBoards = initializeTrelloBoards_practicalUsage();
        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoard(anyList())).thenCallRealMethod();
        when(trelloMapper.mapToBoardDtoList(any())).thenCallRealMethod();
        when(trelloMapper.mapToList(any())).thenCallRealMethod();
        when(trelloMapper.mapToListDto(any())).thenCallRealMethod();
        when(trelloValidator.validateTrelloBoards(any())).thenCallRealMethod();
        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoard();
        // Then
        assertEquals(3,trelloBoardDtos.size());
        assertEquals(3, trelloBoardDtos.get(0).getLists().size());
        assertEquals(TrelloBoardDto.class, trelloBoardDtos.get(0).getClass());
        assertEquals(TrelloListDto.class, trelloBoardDtos.get(0).getLists().get(0).getClass());
        verify(trelloService, times(1)).fetchTrelloBoards();
        verify(trelloMapper, times(1)).mapToBoard(any());
        verify(trelloMapper, times(1)).mapToBoardDtoList(any());
        verify(trelloValidator, times(1)).validateTrelloBoards(any());
    }

    @Test
    void testCreateCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name","description","top","1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1","createdTrelloCardDto","https://test.com");
        when(trelloService.createTrelloCard(any())).thenReturn(createdTrelloCardDto);
        when(trelloMapper.mapToTrelloCard(any())).thenCallRealMethod();
        when(trelloMapper.mapToTrelloCardDto(any())).thenCallRealMethod();
        doCallRealMethod().when(trelloValidator).validateCard(any());
        // When
        CreatedTrelloCardDto trelloCard = trelloFacade.createdCard(trelloCardDto);
        // Then
        assertEquals("createdTrelloCardDto",trelloCard.getName());
        assertEquals("https://test.com",trelloCard.getShortUrl());
        verify(trelloService, times(1)).createTrelloCard(any());
        verify(trelloMapper, times(1)).mapToTrelloCard(any());
        verify(trelloMapper, times(1)).mapToTrelloCardDto(any());
        verify(trelloValidator, times(1)).validateCard(any());
    }

    private List<TrelloBoardDto> initializeTrelloBoards_practicalUsage() {
        TrelloListDto trelloListDto = new TrelloListDto("1","ToDo",false);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto);
        trelloListsDto.add(trelloListDto);
        trelloListsDto.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1","Team",trelloListsDto);
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDto);
        trelloBoardsDto.add(trelloBoardDto);
        trelloBoardsDto.add(trelloBoardDto);
        return trelloBoardsDto;
    }
}
