(ns pass-gen.core
  (:require [clojure.string :as string]
            [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))


(def cli-options
  [
   ["-m" "--min-word-length LENGTH"
    "Use words at least this long."
    :default 2
    :parse-fn #(Integer/parseInt %)
    :validate [#(pos? %) "Must be a positive number"]]

   ["-M" "--max-word-length LENGTH"
    "Use words at most this long."
    :default 6
    :parse-fn #(Integer/parseInt %)
    :validate [#(pos? %) "Must be a positive number"]]

   ["-f" "--word-file"
    "Path to word file (one word per line)."
    :default "/usr/share/dict/words"]

   ["-F" "--word-filter REGEX"
    "Allow only words that match this regular expression. "
    :default #"^[a-z]+$"
    :parse-fn re-pattern]

   ["-h" "--help"]])


(defn usage [options-summary]
  (->> ["Usage: program-name [options]"
        ""
        "Options:"
        options-summary]
       (string/join \newline)))


(defn error-msg [errors]
  (string/join \newline errors))


(defn exit [status msg]
  (println msg)
  (System/exit status))


(defn generate [words word-count]
  (string/join " "
               (repeatedly word-count (partial rand-nth words))))


(defn run
  [{:keys [min-word-length max-word-length word-file word-filter]}]
  (with-open [rdr (clojure.java.io/reader word-file)]
    (let [words
          (vec (for [word (line-seq rdr)
                     :let [word-len (count word)]
                     :when (and
                            (re-matches word-filter word)
                            (<= min-word-length word-len max-word-length))]
                 word))]
      (printf "Found %d words\n" (count words))
      (println (generate words 4)))))


(defn -main
  [& args]
  (let [{:keys [options arguments errors summary]}
        (parse-opts args cli-options)]
    (cond
      (:help options) (exit 0 (usage summary))
      errors (exit 1 (error-msg errors)))
    (run options)))
