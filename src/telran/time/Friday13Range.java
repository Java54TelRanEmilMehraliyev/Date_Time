package telran.time;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Friday13Range implements Iterable<Temporal> {
    private Temporal from;
    private Temporal to;
    
    public Friday13Range(Temporal from, Temporal to) {
    	this.from = from;
    	this.to = to;
    }
	@Override
	public Iterator<Temporal> iterator() {
		
		return new FridayIterator();
	}
	
	private class FridayIterator implements Iterator<Temporal> {
        private Temporal current;
        
        public FridayIterator() {
        	this.current = from;
        }
		@Override
		public boolean hasNext() {
			
			return current.until(to, ChronoUnit.DAYS) >=0; 
		}

		@Override
		public Temporal next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			TemporalAdjuster nextFriday13 = new NextFriday13();
			current = nextFriday13.adjustInto(current);
			return current;
		}
		
	}

}