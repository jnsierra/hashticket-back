package co.ud.ud.hashticket.dto.ticket;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConfirmBuyTicket {
    private Long numberTicket;
    private String confirmNumberTicket;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfirmBuyTicket that = (ConfirmBuyTicket) o;

        if (!numberTicket.equals(that.numberTicket)) return false;
        return confirmNumberTicket.equals(that.confirmNumberTicket);
    }

    @Override
    public int hashCode() {
        int result = numberTicket.hashCode();
        result = 31 * result + confirmNumberTicket.hashCode();
        return result;
    }
}
