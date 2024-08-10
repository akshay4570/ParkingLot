package terminal;

import amount.AmountCalculator;
import amount.AmountCalculatorFactory;
import model.Ticket;
import payment.Payment;
import payment.UPIPayment;
import terminal.Terminal;

public class ExitTerminal extends Terminal {
    private final Payment payment;

    public ExitTerminal(Payment payment) {
        this.payment = payment;
    }

    public Payment showTicket(Ticket ticket){
        AmountCalculator amountCalculator = AmountCalculatorFactory.getAmountCalculator();
        Double amountToBePaid = amountCalculator.calculateTotalAmount(getTotalTimeInHours(ticket.getIssueTime()), ticket.getParkingSpot());
        payment.process(amountToBePaid);
        return payment;
    }

    private static Double getTotalTimeInHours(long issueTime) {
        return (System.currentTimeMillis() - issueTime)/3600000.0;
    }
}
