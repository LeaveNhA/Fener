(ns fener.process)

(defonce toasts
    (atom {}))

(defn register-toast [id toast-id]
    (swap! toasts assoc id toast-id))

(defn get-toast-id [toasts-atom toast-key]
    (get @toasts-atom toast-key :not-valid-key))

(def get-toast-from-local-collection (partial get-toast-id toasts))

(defn check-toast-virtual-callback-for-nil [vcallback]
    (cond
      (nil? vcallback) identity
      (keyword? vcallback) (partial register-toast vcallback)
      :else vcallback))
