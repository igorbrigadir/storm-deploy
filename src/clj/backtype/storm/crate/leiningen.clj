(ns backtype.storm.crate.leiningen
  (:require
   [pallet.resource.remote-file :as remote-file]
   [pallet.action.exec-script :as exec-script]))

;; using a stable version (v2) - not frozen. 
(def download-url "https://raw.github.com/technomancy/leiningen/stable/bin/lein")

(defn install [request]
  (-> request
      (remote-file/remote-file
       "/usr/local/bin/lein"
       :url download-url
       :owner "root"
       :mode 755)
      (exec-script/exec-script
       (export "LEIN_ROOT=1")
       ("/usr/local/bin/lein"))))
