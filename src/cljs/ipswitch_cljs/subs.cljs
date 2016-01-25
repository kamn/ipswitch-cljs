(ns ipswitch-cljs.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
 :name
 (fn [db]
   (reaction (:name @db))))

(re-frame/register-sub
 :slide
 (fn [db]
   (reaction (:slide @db))))

(re-frame/register-sub
 :example-1-state
 (fn [db]
   (reaction (:example-1-state @db))))
