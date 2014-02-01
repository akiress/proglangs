;; Print an environment without looping endlessly.
;; The helper functions printscope, printbinding, and printvalue
;; can be used individually as well.
;; This doesn't work if the environment contains a variable whose
;; value is an environment, such as when running the Scheme interpreter
;; on top of itself.

(define (printenv env)
  (display "(")
  (newline)
  (map printscope env)
  (display ")")
  (newline))

(define (printscope alist)
  (display " (")
  (newline)
  (map printbinding alist)
  (display " )")
  (newline))

(define (printbinding b)
  (display "  (")
  (write (car b))
  (display " ")
  (printvalue (cadr b))
  (display ")")
  (newline))

(define (printvalue v)
  (if (and (pair? v) (eq? (car v) 'closure))
      (begin
	(display "(closure ")
	(write (cadr v))
	(display " ...)"))
      (write v)))
