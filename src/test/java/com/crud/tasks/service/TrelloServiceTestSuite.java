package com.crud.tasks.service;

import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService emailService;
    @Mock
    private AdminConfig adminConfig;

    @Test
    void testFetchTrelloBoards() {
        // Given
        when(trelloClient.getTrelloBoards()).thenReturn(List.of());
        // When
        List<TrelloBoardDto> trelloBoards = trelloService.fetchTrelloBoards();
        // Then
        assertEquals(0,trelloBoards.size());
    }

    @Test
    void testCreateTrelloCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name","desc","top","1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1","cardName","https://test.com");
        when(adminConfig.getAdminMail()).thenCallRealMethod();
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        doNothing().when(emailService).send(any());
        // When
        CreatedTrelloCardDto trelloCard = trelloService.createTrelloCard(trelloCardDto);
        // Then
        assertThat(trelloCard).isNotNull();
        assertEquals("cardName", trelloCard.getName());
        verify(adminConfig, times(1)).getAdminMail();
        verify(trelloClient, times(1)).createNewCard(trelloCardDto);
    }
}
