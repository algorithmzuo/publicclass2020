package test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MadianQuick {

	public static class MedianHolder {
		private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new MaxHeapComparator());
		private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(new MinHeapComparator());

		private void modifyTwoHeapsSize() {
			if (this.maxHeap.size() == this.minHeap.size() + 2) {
				this.minHeap.add(this.maxHeap.poll());
			}
			if (this.minHeap.size() == this.maxHeap.size() + 2) {
				this.maxHeap.add(this.minHeap.poll());
			}
		}

		public void addNumber(int num) {
			if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
				maxHeap.add(num);
			} else {
				minHeap.add(num);
			}
			modifyTwoHeapsSize();
		}

		public Integer getMedian() {
			int maxHeapSize = this.maxHeap.size();
			int minHeapSize = this.minHeap.size();
			if (maxHeapSize + minHeapSize == 0) {
				return null;
			}
			Integer maxHeapHead = this.maxHeap.peek();
			Integer minHeapHead = this.minHeap.peek();
			if (maxHeapSize == minHeapSize) {
				return (maxHeapHead + minHeapHead) / 2;
			}
			return maxHeapSize > minHeapSize ? maxHeapHead : minHeapHead;
		}

	}

	public static class MaxHeapComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			if (o2 > o1) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	public static class MinHeapComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			if (o2 < o1) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	// for test
	public static int[] getRandomArray(int maxLen, int maxValue) {
		int[] res = new int[(int) (Math.random() * maxLen) + 1];
		for (int i = 0; i != res.length; i++) {
			res[i] = (int) (Math.random() * maxValue);
		}
		return res;
	}

	// for test, this method is ineffective but absolutely right
	public static int getMedianOfArray(int[] arr) {
		int[] newArr = Arrays.copyOf(arr, arr.length);
		Arrays.sort(newArr);
		int mid = (newArr.length - 1) / 2;
		if ((newArr.length & 1) == 0) {
			return (newArr[mid] + newArr[mid + 1]) / 2;
		} else {
			return newArr[mid];
		}
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		boolean err = false;
		int testTimes = 200000;
		for (int i = 0; i != testTimes; i++) {
			int len = 30;
			int maxValue = 1000;
			int[] arr = getRandomArray(len, maxValue);
			MedianHolder medianHold = new MedianHolder();
			for (int j = 0; j != arr.length; j++) {
				medianHold.addNumber(arr[j]);
			}
			if (medianHold.getMedian() != getMedianOfArray(arr)) {
				err = true;
				printArray(arr);
				break;
			}
		}
		System.out.println(err ? "Oops..what a fuck!" : "today is a beautiful day^_^");

	}

}
