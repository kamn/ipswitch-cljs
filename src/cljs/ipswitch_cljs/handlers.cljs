(ns ipswitch-cljs.handlers
    (:require [re-frame.core :as re-frame]
              [ipswitch-cljs.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/register-handler
 :slide-forward
 (fn  [db _]
   (update db :slide inc)))

(re-frame/register-handler
 :slide-back
 (fn  [db _]
   (update db :slide #(if (zero? %) 0 (dec %)))))

(re-frame/register-handler
 :example-step-forward
 (fn  [db _]
    (js/console.log "forward")
    (update-in db [:example-1-state :step] #(if (> % 2) 3 (inc %)))))

(re-frame/register-handler
 :example-step-back
 (fn  [db _]
   (update-in db [:example-1-state :step] #(if (zero? %) 0 (dec %)))))

(re-frame/register-handler
 :example-hide
 (fn  [db _]
   (assoc-in db [:example-1-state :show?] false)))
