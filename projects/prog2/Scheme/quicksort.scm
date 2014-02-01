(define (quicksort ls)
	(if (null? ls) '()
		(let ((p (car ls)))
			(append
				(quicksort (lo p ls))
				(list p)
				(quicksort (hi p ls))))))