package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrelloValidatorTestSuite {

    @Mock
    private TrelloValidator trelloValidator;

    @Test
    void testValidateCard_practicalUsage() {
        // Given
        TrelloCard trelloCard = new TrelloCard("name","desc","top","1");
        doCallRealMethod().when(trelloValidator).validateCard(trelloCard);
        // When
        trelloValidator.validateCard(trelloCard);
        // Then
        verify(trelloValidator, times(1)).validateCard(trelloCard);
    }

    @Test
    void testValidateCard_testUsage() {
        // Given
        TrelloCard trelloCard = new TrelloCard("test","desc","top","1");
        doCallRealMethod().when(trelloValidator).validateCard(trelloCard);
        // When
        trelloValidator.validateCard(trelloCard);
        // Then
        verify(trelloValidator, times(1)).validateCard(trelloCard);
    }

    @Test
    void testValidateTrelloBoards() {
        // Given
        List<TrelloList> trelloLists = List.of(new TrelloList("1","ToDo",false));
        List<TrelloBoard> trelloBoards = List.of(new TrelloBoard("1","test",trelloLists),new TrelloBoard("2","name",trelloLists));
        when(trelloValidator.validateTrelloBoards(trelloBoards)).thenCallRealMethod();
        // When
        List<TrelloBoard> validatedBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        // Then
        assertEquals(1,validatedBoards.size());
    }
}
