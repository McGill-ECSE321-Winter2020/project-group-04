package ca.mcgill.ecse321.petadoptionsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;

@ExtendWith(MockitoExtension.class)
public class TestAccountService {

    @Mock
    private AccountRepository accountDAO;

    @InjectMocks
    private AccountService accountService;

    private static final String USERNAME = "Bathsheba_Everdene";

    @BeforeEach
    public void setMockOutput() {
        lenient().when(accountDAO.findAccountByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(USERNAME)) {
                Account account = new Account();
                account.setUsername(USERNAME);
                return account;
            } else {
                return null;
            }
        });
    }

    @Test
    public void testCreateRegularUserAccount() {
        assertEquals(0, accountService.getAllAccounts().size());

        String username = "xXx_mike_xXx";
    }
}
