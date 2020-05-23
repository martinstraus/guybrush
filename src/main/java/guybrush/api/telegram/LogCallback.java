package guybrush.api.telegram;

import org.springframework.stereotype.Component;

/**
 *
 * @author Martín Straus <martinstraus@gmail.com>
 */
@Component
class LogCallback implements Callback {

    @Override
    public void updateReceived(int id) {
        System.out.printf("Received update %d.\n", id);
    }
    
}
