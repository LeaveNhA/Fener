# SULUK
React-Toastify wrapper for the re-frame!

## Install
Add dependency to your project.clj file.

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.scknkkrer/fener.svg)](https://clojars.org/org.clojars.scknkkrer/fener)

## Usage

### Add Dependency
Add dependency to your dependencies vector.

```clojure
...
:dependencies
     [...
       [org.clojars.scknkkrer/fener "0.0.20"]
      ...]
...

```

### Using with Re-Frame
If you want to use `fener` with re-frame, all you have to do is:

Require `fener.re-frame` namespace from your event namespace to load existed fx function that comes with `Fener`

```clojure
(ns perfect.re-frame.app.events
(:require ...
          [fener.re-frame]))

```

The effect named `:fener/toast!` is loaded and ready to use.

```clojure
(re-frame/reg-event-fx
 #_"Sending Login Credentials to API!
    Simple functionality is approving the user!"
 :user/send-login-credentials
 (fn [{:keys [db]} [_ id-cred pass-cred]]
   (let []
     {:db db
      :fener/toast! [:info "Giriş yapılıyor..." {} :login-toast]
      :suluk! [:get "https://mockbin.org/delay/200"
               {:body {:id id-cred :pass pass-cred}}
               [:fail-f #(js/console.error "Başarısız!")
                :done-f #(re-frame/dispatch [:app/login-callback])]]})))

(re-frame/reg-event-fx
 :app/login-callback
 (fn [{:keys [db]} [_ response]]
   (let []
     {:db db
      :app/set-page-with-url "dashboard"
      :fener/alter-toast! [:login-toast {:autoClose 1453
                                         :render "Giriş Başarılı!"
                                         :type "success"}]})))

(re-frame/dispatch [:user/send-login-credentials])
```
`:fener/toast!` is your helper effect that let you can create a toast. It's signature is so fluent; `Keyword toast-type -> Render | String content -> Hash-Map options -> Keyword toast-id -> Nil`.

Toast types are `#{:normal :info :success :warning :error}`.

`:fener/alter-toast!` is helper with signature: `Keyword toast-id -> Hash-Map toast-settings -> Nil` for the manage your live toasts with given options on there; [React-Toastify](https://github.com/fkhadra/react-toastify#update-a-toast).

Note: Suluk is `Clojurescript` library that gives you more idiomatic Fetch API. If you interested, look at [LeaveNhA/Suluk](https://github.com/LeaveNhA/suluk).

# TODO

- [~] Complete the README.md!

# THANK
Thank you all `Clojurians`, this library aims to give something back to our perfect community!
