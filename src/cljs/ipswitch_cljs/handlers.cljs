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
   (js/console.log "inc")
   (update db :slide inc)))

(re-frame/register-handler
 :slide-back
 (fn  [db _]
   (js/console.log "dec")
   (update db :slide #(if (zero? %) 0 (dec %)))))
