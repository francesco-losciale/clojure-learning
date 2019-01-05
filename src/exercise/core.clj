(use 'clojure.repl)
(ns exercise.core)

; alt+shift+p send line to REPL
; alt+shift+l send file to REPL

; exercise 1
(+ 7654 1234)
(rem (+ 7 (* 3 4) 5) 10)
(mod (+ 7 (* 3 4) 5) 10)

(doc rem)
(doc mod)
(find-doc "stack trace")                                    ; find info about how to print stack trace
(clojure.stacktrace/e)                                              ; print stack trace of last exception


(defn greet [] print "Hello")
(greet)

(def greet (fn [] (print "Hello2")))
(greet)

; (def greet #("Hello3"))                                   ; uncomment this and see the error
(greet)




;3) Define a function greeting which:
; Given no arguments, returns "Hello, World!"
; Given one argument x, returns "Hello, x!"
; Given two arguments x and y, returns "x, y!"


(use 'clojure.repl)
(doc str)
(defn greeting
  ([] "Hello, World!")
  ([x] (str "Hello, " x "!"))
  ([x,y] (str x ", " y "!"))
  )

(greeting)

;; For testing
(assert (= "Hello, World!" (greeting)))
(assert (= "Hello, Clojure!" (greeting "Clojure")))
(assert (= "Good morning, Clojure!" (greeting "Good morning" "Clojure")))


(defn do_nothing [x] x)
(do_nothing 1)
(doc identity)
(source identity)

(defn always-thing [& l] :thing)
(always-thing 1 2 3)


;6) Define a function make-thingy which takes a single argument x. It should return another function,
;   which takes any number of arguments and always returns x.

(defn make-thingy [x] (fn [& l] x))

;; Tests
(let [n (rand-int Integer/MAX_VALUE)
      f (make-thingy n)]
  (assert (= n (f)))
  (assert (= n (f :foo)))
  (assert (= n (apply f :foo (range)))))

(source constantly)

; 7) Define a function triplicate which takes another function and calls it three times, without any arguments.

(defn triplicate [f & args]
  ((fn f1 [] (apply f args)))
  ((fn f2 [] (apply f args)))
  ((fn f3 [] (apply f args))))
(triplicate #(print "Ciao"))
(doc do)


; 8) Define a function opposite which takes a single argument f. It should return another function which takes
; any number of arguments, applies f on them, and then calls not on the result.
; The not function in Clojure does logical negation.

(defn opposite [f] (fn [& args] (not (apply f args))))
((opposite identity) true)

((complement identity) true)
(doc complement)



; 9) Define a function triplicate2 which takes another function and any number of arguments, then calls
; that function three times on those arguments. Re-use the function you defined in the earlier triplicate
; exercise.
(defn triplicate [f & args]
  ((fn f1 [] (apply f args)))
  ((fn f2 [] (apply f args)))
  ((fn f3 [] (apply f args))))
(defn triplicate2 [f & args] (apply triplicate f args))
(triplicate2 print "Ciao")


;10) Using the java.lang.Math class (Math/pow, Math/cos, Math/sin, Math/PI), demonstrate the
; following mathematical facts:
;   The cosine of pi is -1
;   For some x, sin(x)^2 + cos(x)^2 = 1

(assert (= (Math/cos Math/PI) -1.0))
(defn f [x] (Math/round (+ (Math/pow (Math/sin x) 2) (Math/pow (Math/cos x) 2))))
(assert (= (f 9) 1))


;11) Define a function that takes an HTTP URL as a string, fetches that URL from the web, and returns the
; content as a string. Hint: Using the java.net.URL class and its openStream method. Then use the Clojure
; slurp function to get the content as a string.
;
;(defn http-get [url]
;  ___)
;(assert (.contains (http-get "http://www.w3.org") "html"))

(defn http-get [url] (slurp (.openStream (java.net.URL. url))))
(assert (.contains (http-get "http://www.w3.org") "html"))
(doc slurp)
(doc clojure.java.io/reader)

;In fact, the Clojure slurp function interprets its argument as a URL first before trying it as a file name. Write a simplified http-get:
;
;(defn http-get [url]
;  ___)

(defn http-get [url] (slurp (java.net.URL. url)))
(assert (.contains (http-get "http://www.w3.org") "html"))

;12) Define a function one-less-arg that takes two arguments:
;
;f, a function
;x, a value
;
;and returns another function which calls f on x plus any additional arguments.
;
;(defn one-less-arg [f x]
;  (fn [& args] ___))

(defn one-less-arg [f x] (fn [& args] (f x args)))
(doc partial)

;13) Define a function two-fns which takes two functions as arguments, f and g. It returns another
; function which takes one argument, calls g on it, then calls f on the result, and returns that.
;
;That is, your function returns the composition of f and g.
;
;(defn two-fns [f g]
;  ___)

(defn two-fns [f g] (fn [arg] (f arg) (g arg)))
(defn ciao-1 [arg] (print (str "Ciao 1 " arg)))
(defn ciao-2 [arg] (print (str "Ciao 2 " arg)))
((two-fns ciao-1 ciao-2) "Francesco")





