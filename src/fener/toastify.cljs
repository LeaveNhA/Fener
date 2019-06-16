(ns fener.toastify
  (:require [reagent.core :as r]
            [react-toastify :as toastify]))

(def toast toastify/toast)
(def toast-info toastify/toast.info)
(def toast-success toastify/toast.success)
(def toast-warning toastify/toast.warning)
(def toast-error toastify/toast.warning)
(def toast-update toastify/toast.update)

(def ^{:doc "Toast Container for the main use-case!"}
  ToastContainer (r/adapt-react-class toastify/ToastContainer))
