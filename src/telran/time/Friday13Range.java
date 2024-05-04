package telran.time;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Friday13Range implements Iterable<Temporal> {
    private LocalDate from;
    private LocalDate to;
    
    public Friday13Range(Temporal from, Temporal to) {
    	this.from = LocalDate.from(from);
    	this.to = LocalDate.from(to);
    }
	@Override
	public Iterator<Temporal> iterator() {
		
		return new FridayIterator();
	}
	
	private class FridayIterator implements Iterator<Temporal> {
        private LocalDate current;
        
        public FridayIterator() {
        	this.current = from;
        }
		@Override
		public boolean hasNext() {
			
			return current.isBefore(to) || current.equals(to);
		}

		@Override
		public Temporal next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			TemporalAdjuster nextFriday13 = new NextFriday13();
			current = (LocalDate) nextFriday13.adjustInto(current);
			return current;
		}
		
	}

}