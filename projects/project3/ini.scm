;;; the comparison operations eqv? and equal?
;; eqv?
(define eqv?
  (lambda (x y)
    (if (and (number? x) (number? y))
      (= x y)
      (eq? x y))))

;; equal?
(define (equal? a b)
  (cond ((eqv? a b)
    #t)
  ((and (pair? a)
    (pair? b)
    (equal? (car a) (car b))
    (equal? (cdr a) (cdr b)))
  #t)
  (else
   #f)))
;;; the n-ary integer comparison operations =, <, >, <=, >=
;; less than
(define (<<< . l)
  (if (null? l)
    (b<)
    (b< (car l)
      (cond 
        ((if (null? (cdr l)) car l))
        ((apply <<< (cdr l))) cdr l))))
; ;; greater than
; (define (> . l)
;   (if (null? l) 0
;     (b> (car l) (apply > (cdr l)))))
; ;; equals
; (define (= . l)
;   (if (null? l) 0
;     (b= (car l) (apply = (cdr l)))))
;; less than or equal to (these or wrong but I copy/pasted the </> and modified
;; for now. I am guessing that I should do someele with a boolean or once I have
;; it defined, if possible at least
; (define (<= . l)
;   (if (null? l) 0
;     (b< (car l) (apply <= (cdr l)))))
; ;; greater than or equal to (same as <=)
; (define (>= . l)
;   (if (null? l) 0
;     (b< (car l) (apply >= (cdr l)))))

;;; the test predicates zero?, positive?, negative?, odd?, even?
;; zero?
(define zero?
  (lambda (x)
    (if (= x 0)
      #t
      #f)))

;; positive?
(define positive?
  (lambda (x)
    (if (> x 0)
      #t
      #f)))

;; negative?
(define negative?
  (lambda (x)
    (if (< x 0)
      #t
      #f)))

;; odd?
(define odd?
  (lambda (x)
    (cond 
      ((= x 0) #f)
      ((= x 1) #t)
      ((positive? x) (odd? (- x 2)))
      ((negative? x) (odd? (+ x 2))))))

;; even?
(define even?
  (lambda (x)
    (cond
      ((= x 0) #t)
      ((= x 1) #f)
      ((positive? x) (even? (- x 2)))
      ((negative? x) (even? (+ x 2))))))

;;; the n-ary arithmetic operations max, min, +, -, *
;; max
(define (max . l)
  (if (= (length l) 1)
    (car l)
    (if (null? l)
      (write "wrong number of arguments")
      (if (> (car l) (apply max (cdr l)))
        (car l)
        (apply max (cdr l))))))

;; min
(define (min . l)
  (if (= (length l) 1)
    (car l)
    (if (null? l)
      (write "wrong number of arguments")
      (if (< (car l) (apply min (cdr l)))
        (car l)
        (apply min (cdr l))))))

;; addition
(define (+ . l)
  (if (null? l) 0
    (b+ (car l) (apply + (cdr l)))))

;; subtraction
(define (- . l)
  (if (null? l) 0
    (b- (car l) (apply + (cdr l)))))

;; multiplication
(define (* . l)
  (if (null? l) 1
    (b* (car l) (apply * (cdr l)))))

;;; the boolean operations not, and, or
;; and
(define dand 
  (lambda (x y)
    (cond (x (cond (y #t)
      (else #f)))
    (else #f))))

;; or
(define or
  (lambda (x y)
    (cond (x (cond (y #t)
      (else #t)))
    (else #f))))

;; not
(define not
 (lambda (x)
  (cond (x #f)
    (else #t))))

;;; the list functions caar, cadr, ...., cddddr, list, length, append, reverse
(define caar (lambda (x) (car (car x))))
(define cadr (lambda (x) (car (cdr x))))
(define cdar (lambda (x) (cdr (car x))))
(define cddr (lambda (x) (cdr (cdr x))))
(define caaar (lambda (x) (car (car (car x)))))
(define caadr (lambda (x) (car (car (cdr x)))))
(define cadar (lambda (x) (car (cdr (car x)))))
(define caddr (lambda (x) (car (cdr (cdr x)))))
(define cdaar (lambda (x) (cdr (car (car x)))))
(define cdadr (lambda (x) (cdr (car (cdr x)))))
(define cddar (lambda (x) (cdr (cdr (car x)))))
(define cdddr (lambda (x) (cdr (cdr (cdr x)))))
(define caaaar (lambda (x) (car (car (car (car x))))))
(define caaadr (lambda (x) (car (car (car (cdr x))))))
(define caadar (lambda (x) (car (car (cdr (car x))))))
(define caaddr (lambda (x) (car (car (cdr (cdr x))))))
(define cadaar (lambda (x) (car (cdr (car (car x))))))
(define cadadr (lambda (x) (car (cdr (car (cdr x))))))
(define caddar (lambda (x) (car (cdr (cdr (car x))))))
(define cadddr (lambda (x) (car (cdr (cdr (cdr x))))))
(define cdaaar (lambda (x) (cdr (car (car (car x))))))
(define cdaadr (lambda (x) (cdr (car (car (cdr x))))))
(define cdadar (lambda (x) (cdr (car (cdr (car x))))))
(define cdaddr (lambda (x) (cdr (car (cdr (cdr x))))))
(define cddaar (lambda (x) (cdr (cdr (car (car x))))))
(define cddadr (lambda (x) (cdr (cdr (car (cdr x))))))
(define cdddar (lambda (x) (cdr (cdr (cdr (car x))))))
(define cddddr (lambda (x) (cdr (cdr (cdr (cdr x))))))

;; list
(define (list . l)
  (if (null? l)
    '()
    l))

;; length
(define (length x)
 (cond ((null? x) 0)
  ((+ 1 (length (cdr x))))))

;; append
(define (append list1 list2)
 (cond ((null? list1) list2)
   ((cons (car list1)
     (append (cdr list1) list2)))))

;; reverse
(define (reverse lis)
 (if (null? lis)
   '()
   (append (reverse (cdr lis))
     (list (car lis)))))

;;; the set and association list operations memq, memv, member, assq, assv, assoc
;; memq
(define (memq ele lis)
 (if (null? lis)
   #f
   (if (eq? (car lis) ele)
     lis
     (memq ele (cdr lis)))))

;; memv
(define (memv ele lis)
 (if (null? lis)
   #f
   (if (eqv? (car lis) ele)
     lis
     (memv ele (cdr lis)))))


;; member
(define (member ele lis)
 (if (null? lis)
   #f
   (if (equal? (car lis) ele)
     lis
     (member ele (cdr lis)))))

;; assq
(define (assq ele lis)
 (if (null? lis)
   #f
   (if (eq? (car (car lis)) ele)
     (car lis)
     (assq ele (cdr lis)))))

;; assv
(define (assv ele lis)
 (if (null? lis)
   #f
   (if (eqv? (car (car lis)) ele)
     (car lis)
     (assv ele (cdr lis)))))

;; assoc
(define (assoc ele lis)
 (if (null? lis)
   #f
   (if (equal? (car (car lis)) ele)
     (car lis)
     (assoc ele (cdr lis)))))

;;; the higher-order functions map and for-each
;; map
(define (map ele lis)
 (cond ((null? lis)
  '())
 ((pair? lis)
  (cons (ele (car lis))
    (map ele (cdr lis))))))

;; for-each
(define (for-each1 ele lis)
   (cond ((null? (cdr lis))  ; one-element list?
    (ele (car lis)))
   (else
    (ele (car lis))
    (for-each1 ele (cdr lis)))))