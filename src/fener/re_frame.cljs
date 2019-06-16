(ns fener.re-frame
  (:require [fener.process :as process]
            [fener.toastify :as toastify]
            [re-frame.core :as re-frame]))


(re-frame/reg-fx
   :fener/toast!
   (fn [[t c opt vcallback]]
     (let [vcallback (process/check-toast-virtual-callback-for-nil vcallback)
           generated-toast-id ((case t
                                 :normal toastify/toast
                                 :info toastify/toast-info
                                 :success toastify/toast-success
                                 :warning toastify/toast-warning
                                 :error toastify/toast-error) c opt)]
       (when ^boolean goog.DEBUG
         (js/console.info "Latest toast id with " vcallback ": " generated-toast-id "."))
       (vcallback generated-toast-id))))

(re-frame/reg-fx
   :fener/alter-toast!
   (fn [[toast-key new-opts]]
     (let [toast-id (process/get-toast-from-local-collection toast-key)
           new-opts (clj->js new-opts)]
       (when ^boolean goog.DEBUG
         (js/console.info "Altering toast with " toast-id ":" (clj->js new-opts)))
       (toastify/toast-update toast-id new-opts))))
