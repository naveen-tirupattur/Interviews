package my.interview.samples;

import java.util.Iterator;
import java.util.List;

public class IteratorProblem {

	Iterator<Object> flatIterator;

	public Iterator<Object> getIterator(List<List<Object>> lists) {
		final Iterator<List<Object>> outerIterator = lists.iterator();

		return new Iterator<Object>() {

			@SuppressWarnings("unchecked")
			@Override
			public boolean hasNext() {
				if (flatIterator == null) {
					while (outerIterator.hasNext()) {
						flatIterator = (Iterator<Object>) outerIterator.next();
					}
				}

				return flatIterator.hasNext();
			}

			@Override
			public Object next() {
				if (flatIterator == null) {
					while (outerIterator.hasNext()) {
						flatIterator = (Iterator<Object>) outerIterator.next();
					}
				}

				return flatIterator.next();
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub

			}

		};
	}

}
