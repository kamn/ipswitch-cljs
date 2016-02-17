(ns ipswitch-cljs.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com :refer [v-box h-box title label button 
                                              single-dropdown input-text hyperlink-href
                                              modal-panel]]))

(defn slide-title []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [title
       ;;:label "Why you can quickly develop websites with Clojurescript"
       :label "Clojurescript: A Dynamic Development Language"
       ;;:label "Clojurescript For Fun and Profit"
       :level :level1])))

(defn slide-opening []
  (fn []
    [v-box
     :children [[slide-title]]]))

(defn slide-goals []
  (fn []
    [v-box
     :children [
                [title
                 :label "Goals"
                 :level :level1]
                [title
                 :label "* Demo"
                 :level :level1]
                [title
                 :label "* What is Clojurescript?"
                 :level :level1]
                [title
                 :label "* What other benefits does it offer?"
                 :level :level1]
                [title
                 :label "* \"What about...\" (Concerns)"
                 :level :level1]]]))

(defn slide-fake-bug []
  (fn []
    [v-box 
      :children [
                 [title 
                  :label "Bug Report on adding SNMP creds"
                  :level :level1]
                 [title
                  :label "\"The read community on snmpv1/v2 credentials are missing\""
                  :level :level2]]]))



(defn slide-end []
  (fn []
    [v-box
     :children [
                [title
                 :label "Fin"
                 :level :level1]]]))


#_(defn simple-modal-btns [& args]
            (js/console.log args)
    (fn [& args]
      (let [btn-pairs (partition 2 args)]
        (js/console.log btn-pairs)
        (println btn-pairs)
        (mapcat #([button :label %1 :on-click %2]) btn-pairs))))

(defn simple-modal-btns [& args]
    (let [btn-pairs (partition 2 args)]
        (mapv #(vec [button :label (first %) :on-click (second %)]) btn-pairs)))

#_(defn simple-modal-btns [text1 fn1 text2 fn2]
    (fn []
      [[button :label text1
               :on-click fn1]
       [button :label text2
               :on-click fn2]]))


(defn example-action-section []
  (fn []
    [v-box
     :gap "10px"
     :children [
                 [h-box
                  :gap "10px"
                  :children [[label :label "Select an Action"]
                             [single-dropdown 
                              :on-change #(js/console.log %)
                              :model 1
                              :width "150px"
                              :choices [{:id 1 :label "Add Monitor"}
                                        {:id 2 :label "Add Report"}
                                        {:id 3 :label "Add Dashboard"}
                                        {:id 4 :label "Add Credential"}]]]]
                 [h-box
                  :gap "10px"
                  :children ;;(simple-modal-btns "Cancel" #() "Next" #(re-frame/dispatch [:example-step-forward]))
                            [[button :label "Cancel"]
                             [button :label "Next"
                                     :on-click #(re-frame/dispatch [:example-step-forward])]]]]]))
    

(defn example-cred-section []
  (fn []
    [v-box
     :gap "10px"
     ;;:align :end
     :children [
                 [h-box
                  :gap "10px"
                  ;;:align :center
                  :children [[label :label "Select a Credential Type"]
                             [single-dropdown 
                              :on-change #(js/console.log %)
                              :model 1
                              :width "150px"
                              :choices [{:id 1 :label "SSH"}
                                        {:id 2 :label "SNMP v1"}
                                        {:id 3 :label "SNMP v2"}
                                        {:id 4 :label "SNMP v3"}
                                        {:id 5 :label "VMware"}]]]]
                 [h-box
                  :gap "10px"
                  :children [[button :label "Cancel"
                                     ;;:class "btn-danger"
                                     :on-click #(re-frame/dispatch [:example-step-back])]
                             [button :label "Next"
                                     ;;:class "btn-primary"
                                     :on-click #(re-frame/dispatch [:example-step-forward])]]]]]))


(defn example-snmp-section []
  (fn []
    [v-box
     :gap "10px"
     :children [
                 [v-box
                  :gap "10px"
                  :children [;;[label :label "Name"]
                             [input-text :model "" :on-change #() :placeholder "Name"]
                             ;;[input-text :model "" :on-change #() :placeholder "Read Community"]
                             [input-text :model "" :on-change #() :placeholder "Write Community"]
                             [input-text :model "" :on-change #() :placeholder "Timeout"]]]
                 [h-box
                  :gap "10px"
                  :children [[button :label "Cancel"
                                     ;;:class "btn-danger"
                                     :on-click #(re-frame/dispatch [:example-step-back])]
                             [button :label "Next"]]]]]))
                                     ;;:class "btn-primary"]]]]]))
                                        


(defn slide-first-example []
  (let [state (re-frame/subscribe [:example-1-state])]
    (fn []
      (when (:show? @state)
        [modal-panel
          :class "white-modal" 
          :backdrop-on-click #(re-frame/dispatch [:example-hide])
          :child (cond 
                  (= 0 (:step @state)) [example-action-section]
                  (= 1 (:step @state)) [example-cred-section]
                  (= 2 (:step @state)) [example-snmp-section])]))))


;; Takes ~9 seconds
;; Vs <1 second to change code and look over
(defn slide-reload-time [] 
  (fn []
    [v-box
     :children [
                [title
                 :label "Timesaver: Reload Time"
                 :level :level1]
                [title
                 :label "* To reload and return to previous state is ~9 seconds"
                 :level :level1]
                [title
                 :label "* To change code and see change it is < 1 second"
                 :level :level1]
                [title
                 :label "* Web Devs reload browsers a few dozen of times per hour"
                 :level :level1]
                [title
                 :label "* Quicker Feedback leads to quicker development time"
                 :level :level1]]]))

(defn slide-feedback []
    (fn []
      [v-box
       :children [
                  [title
                   :label "Timesaver: Visual Feedback"
                   :level :level1]
                  [title
                   :label "* Quicker Feedback leads to quicker development time"
                   :level :level1]
                  [title
                   :label "* To change code and see change it is < 1 second"
                   :level :level1]
                  [title
                   :label "* Web Devs reload browsers a few dozen of times per hour"
                   :level :level1]]]))
(defn slide-how-demo []
    (fn []
      [v-box
       :children [
                  [title
                   :label "How was the Demo relate to Clojurescript?"
                   :level :level1]
                  [title
                   :label "Written in Clojurescript..."
                   :level :level1]
                  [title
                   :label "using Reagent(a wrapper for React)..."
                   :level :level1]
                  [title
                   :label "and the re-frame framework..."
                   :level :level1]
                  [title
                   :label "being reload with a tool called Figwheel."
                   :level :level1]
                  [title
                   :label "This is only able to happen because of the nature of Clojurescript"
                   :level :level1]]]))

(def dyn-dev-text (str "Clojurescript is a dynamic environment you can interact with. "
                       "Almost all of the language constructs are reified, and "
                       "thus can be examined and changed. You can grow your "
                       "program, with data loaded, adding features, fixing "
                       "bugs, testing, in an unbroken stream."))

(def fp-text (str "Clojurescript provides the tools to avoid mutable state, provides "
                  "functions as first-class objects, and emphasizes recursive"
                  " iteration instead of side-effect based looping. Clojurescript"
                  " is impure, yet stands behind the philosophy that programs "
                  "that are more functional are more robust."))

(def lisp-text (str "Clojurescript is a member of the Lisp family of languages. "
                  "Many of the features of Lisp have made it into other "
                  "languages, but Lisp's approach to code-as-data and its macro "
                  "system still set it apart. Additionally, Clojurescript’s maps, "
                  "sets, and vectors are as first class in Clojurescript as lists are in Lisp."))

(def r-poly-text (str "Systems that utilize runtime polymorphism are easier to "
                      "change and extend. Clojurescript offers simple, powerful and "
                      "flexible mechanisms for runtime polymorphism. Clojurescript’s "
                      "protocols and datatypes features add mechanisms for "
                      "abstraction and data structure definition with no "
                      "compromises vs the facilities of the host platform."))

(defn slide-intro-cljs []
    (fn []
      [v-box
       :children [
                  [title
                   :label "What is Clojurescript?"
                   :level :level1]
                  [title
                   :label "Dynamic Development"
                   :level :level1]
                  [title
                   :label dyn-dev-text
                   :level :level2]
                  [title
                   :label "Functional Programming"
                   :level :level1]
                  [title
                   :label fp-text
                   :level :level2]
                  [title
                   :label "Lisp"
                   :level :level1]
                  [title
                   :label lisp-text
                   :level :level2]
                  [title
                   :label "Runtime Polymorphism"
                   :level :level1]
                  [title
                   :label r-poly-text
                   :level :level2]]]))

(defn slide-intro-cljs-personal []
    (fn []
      [v-box
       :children [
                  [title
                   :label "What is Clojurescript to me?"
                   :level :level1]
                  [title
                   :label "* Functions are key unit of composition"
                   :level :level1]
                  [title
                   :label "* Expressions over statements"
                   :level :level1]
                  [title
                   :label "* Stateless over Stateful"
                   :level :level1]
                  [title
                   :label "* Immutability over mutability"
                   :level :level1]
                  [title
                   :label "* Dynamic Development"
                   :level :level1]]]))

(defn slide-example-code []
    (fn []
      [v-box
       :children [
                  [title
                   :label "Some Example Code"
                   :level :level1]
                  [hyperlink-href
                           :label "Himera"
                           :target "_blank"
                           :href "https://himera.herokuapp.com/synonym.html"]]]))

(defn slide-expr-over-statement []
    (fn []
      ;;http://fsharpforfunandprofit.com/posts/expressions-vs-statements/
      ;;http://lambda-the-ultimate.org/node/1044
      ;;https://visualstudiomagazine.com/Articles/2011/04/01/pcnet_Using-LINQ.aspx
      [v-box
       :children [
                  [title
                   :label "Expressions Over Statements"
                   :level :level1]
                  [title
                   :label "* Expressions have some value"
                   :level :level1]
                  [title
                   :label "* Statements is called for side-effects (IO, change variable)"
                   :level :level1]
                  [title
                   :label "* Forces you to write small units of composition"
                   :level :level1]
                  [title
                   :label "* LINQ in C#"
                   :level :level1]]]))

(defn slide-stateless-over-state []
    (fn []
      ;;http://stackoverflow.com/questions/844536/advantages-of-stateless-programming
      ;;http://programmers.stackexchange.com/questions/101337/whats-the-difference-between-stateful-and-stateless
      [v-box
       :children [
                  [title
                   :label "Stateless over Stateful"
                   :level :level1]
                  [title
                   :label "* Every transaction is performed as if it were being done for the very first time"
                   :level :level1]
                  [title
                   :label "* Values"
                   :level :level1]
                  [title
                   :label "* Previous transactions are remembered and may affect the current transaction"
                   :level :level1]]]))

(defn slide-immut-over-mut []
    (fn []
      ;;https://facebook.github.io/react/docs/advanced-performance.html
      ;;
      [v-box
       :children [
                  [title
                   :label "Immutability over Mutability"
                   :level :level1]
                  [title
                   :label "\"It is expensive\""
                   :level :level1]
                  [hyperlink-href
                           :label "React Adv Performance"
                           :target "_blank"
                           :href "https://facebook.github.io/react/docs/advanced-performance.html#immutable-js-to-the-rescue"]
                  [title
                   :label "\"So, Immutable data structures provides you a cheap and less verbose way to track changes... Therefore, if we [use immutable data we will] get a nice boost in perf.\""
                   :level :level2]]]))

(defn slide-react []
    (fn []
      ;;TODO - 
      [v-box
       :children [
                  [title
                   :label "React's place"
                   :level :level1]
                  [title
                   :label "* Transforms values into view (like a function)"
                   :level :level1]
                  [title
                   :label "* Functionally stateless"
                   :level :level1]
                  [title
                   :label "* Major improvments because of immutability "
                   :level :level1]]]))

(defn slide-reagent []
    (fn []
      ;;TODO - 
      ;;IDEA - live code example
      ;;https://github.com/reactjs/react-tutorial/blob/master/public/scripts/example.js
      [v-box
       :children [
                  [title
                   :label "Reagent"
                   :level :level1]
                  [hyperlink-href
                           :label "Site"
                           :target "_blank"
                           :href "http://reagent-project.github.io/"]
                  [title
                   :label "Reagent provides a minimalistic interface between ClojureScript and React. It allows you to define efficient React components using nothing but plain ClojureScript functions and data, that describe your UI using a Hiccup-like syntax."
                   :level :level2]]]))

(defn slide-re-frame []
    (fn []
      ;;TODO - 
      [v-box
       :children [
                  [title
                   :label "Reframe"
                   :level :level1]
                  [hyperlink-href
                           :label "re-frame Github"
                           :target "_blank"
                           :href "https://github.com/Day8/re-frame"]
                  [title
                   :label "({events} ==>) app-db  -->  components -->  Reagent  -->  React  -->  DOM ==> {events}"
                   :level :level2]]]))

(defn slide-figwheel []
    (fn []
      ;;TODO - 
      [v-box
       :children [
                  [title
                   :label "Figwheel"
                   :level :level1]
                  [title
                   :label "Figwheel builds your ClojureScript code and hot loads it into the browser as you are coding!"
                   :level :level2]
                  [hyperlink-href
                           :label "Figwheel Github"
                           :target "_blank"
                           :href "https://github.com/bhauman/lein-figwheel"]
                  [hyperlink-href
                           :label "Figwheel Example"
                           :target "_blank"
                           :href "https://youtu.be/j-kj2qwJa_E?t=7m55s"]]]))

(defn slide-concerns []
    (fn []
      ;;TODO - 
      ;;https://www.youtube.com/watch?v=gsffg5xxFQI
      ;;TODO - Give Derek Slager credit
      [v-box
       :children [
                  [title
                   :label "Concerns"
                   :level :level1]]]))

(defn slide-concern-syntax []
    (fn []
      ;;TODO - 
      ;;https://www.youtube.com/watch?v=gsffg5xxFQI
      [v-box
       :children [
                  [title
                   :label "Syntax"
                   :level :level1]
                  [title
                   :label "fn(arg1, arg2, arg3) => (fn arg1 arg2 arg3)"
                   :level :level2]]]))

;;TODO -  Numbers on Github
(defn slide-concern-community []
    (fn []
      ;;TODO - 
      ;;https://www.youtube.com/watch?v=gsffg5xxFQI
      [v-box
       :children [
                  [title
                   :label "Community"
                   :level :level1]
                  [hyperlink-href
                           :label "Newest 'clojurescript' questions"
                           :target "_blank"
                           :href "http://stackoverflow.com/questions/tagged/clojurescript"]
                  [hyperlink-href
                           :label "Newest 'coffeescript' questions"
                           :target "_blank"
                           :href "http://stackoverflow.com/questions/tagged/coffeescript"]]]))

(defn slide-concern-debugging []
    (fn []
      ;;TODO - 
      ;;https://www.youtube.com/watch?v=gsffg5xxFQI
      [v-box
       :children [
                  [title
                   :label "Debugging"
                   :level :level1]
                  [title
                   :label "Source maps"
                   :level :level2]]]))

(defn slide-concern-bloat []
    (fn []
      ;;TODO - 
      ;;https://www.youtube.com/watch?v=gsffg5xxFQI
      [v-box
       :children [
                  [title
                   :label "Bloat"
                   :level :level1]
                  [title
                   :label "Goal is to target jQuery replacement"
                   :level :level2]]]))

(defn slide-concern-tooling []
    (fn []
      ;;TODO - 
      ;;https://www.youtube.com/watch?v=gsffg5xxFQI
      [v-box
       :children [
                  [title
                   :label "Tooling"
                   :level :level1]
                  [title
                   :label "One main tool: Lein"
                   :level :level2]]]))

(defn slide-concern-interop []
    (fn []
      ;;TODO - 
      ;;https://www.youtube.com/watch?v=gsffg5xxFQI
      [v-box
       :children [
                  [title
                   :label "Interop"
                   :level :level1]
                  [title
                   :label "(js/outsideFn arg1 arg2)"
                   :level :level2]
                  [title
                   :label "cljsjs"
                   :level :level2]]]))


;; From State of Clojure 2015
(defn slide-concern-viability []
    (fn []
      ;;TODO - 
      ;;https://www.youtube.com/watch?v=gsffg5xxFQI
      [v-box
       :children [
                  [title
                   :label "Viability"
                   :level :level1]
                  [hyperlink-href
                           :label "Companies usering Clojurescript"
                           :target "_blank"
                           :href "https://github.com/clojure/clojurescript/wiki/Companies-Using-ClojureScript"]
                  [title
                   :label "Parent language is Clojure. As long as Clojure exists so will Clojurescript."
                   :level :level2]
                  [hyperlink-href
                           :label "Companies usering Clojure"
                           :target "_blank"
                           :href "http://clojure.org/community/companies"]]]))


(defn select-slide [val]
  (nth 
    [slide-opening
     slide-goals
     slide-fake-bug
     slide-first-example
     slide-reload-time
     slide-how-demo
     slide-intro-cljs
     slide-example-code
     slide-intro-cljs-personal
     slide-expr-over-statement
     slide-stateless-over-state
     slide-immut-over-mut
     slide-react
     slide-reagent
     slide-re-frame
     slide-figwheel
     slide-concerns
     slide-concern-syntax
     slide-concern-community
     slide-concern-debugging
     slide-concern-bloat
     slide-concern-tooling
     slide-concern-interop
     slide-concern-viability]
    val
    slide-end))


(defn main-panel []
  (let [slide-num (re-frame/subscribe [:slide])]
    (fn []
      [v-box
       :align :center
       :justify :center
       :height "100%"
       :class "main-view"
       :children [[(select-slide @slide-num)]]
       :attr {:tabIndex 0
              :on-key-down (fn [e] 
                            (cond
                             ;; right arrow
                             (= e.keyCode 39) (re-frame/dispatch [:slide-forward])
                             ;; left arror
                             (= e.keyCode 37) (re-frame/dispatch [:slide-back])))}])))
                          
            
