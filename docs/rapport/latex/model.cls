\NeedsTeXFormat{LaTeX2e}[1995/12/01]
\ProvidesClass{model}[2023/04/24 v0.1, Classe pour le modèle LaTeX]

\DeclareOption*{\PassOptionsToClass{\CurrentOption}{extarticle}}
\ProcessOptions\relax

\LoadClass{extarticle}

\RequirePackage[a-1b]{pdfx}
\RequirePackage{hyperref}
\RequirePackage{styles}
\RequirePackage{accsupp}

\RequirePackage{csquotes}
\RequirePackage[backend=biber, natbib=true, style=numeric, sorting=none]{biblatex}
\addbibresource{references.bib}

\RequirePackage[fixed]{fontawesome5}
\RequirePackage{ifxetex,ifluatex}
\RequirePackage{scrlfile}
\RequirePackage{xparse}
\RequirePackage{titlesec}
\RequirePackage{minted}
\RequirePackage{caption}
\RequirePackage{chngcntr}
\RequirePackage{enumitem}

\RequirePackage{xcolor}
\usemintedstyle{autumn}
\definecolor{DarkGrey}{gray}{0.075}
\definecolor{LightGrey}{gray}{0.925}

\RequirePackage{tocloft}
\renewcommand{\cftsecleader}{\hspace*{1em}\cftdotfill{0.95\cftdotsep}}
\renewcommand{\cftsubsecleader}{\hspace*{1em}\cftdotfill{0.95\cftdotsep}}
\renewcommand{\cftsubsubsecleader}{\hspace*{1em}\cftdotfill{0.95\cftdotsep}}
\renewcommand{\cftfigleader}{\hspace*{1em}\cftdotfill{0.95\cftdotsep}}
\renewcommand{\cfttableader}{\hspace*{1em}\cftdotfill{0.95\cftdotsep}}


\renewcommand{\arraystretch}{2}

\RequirePackage{array,tabularx}
\RequirePackage{longtable}

\hypersetup{
    colorlinks=true,
    linkcolor=black,
    filecolor=black,
    urlcolor=black,
    citecolor=black, % Définit la couleur du texte des hyperliens de citation sur noir
    citebordercolor=white, % Définit la couleur de la bordure des hyperliens de citation sur blanc (pas de bordure)
    linktoc=all
}

\newcommand{\policeEntete}[1]{\fontsize{12}{16}\selectfont\noindent #1}
\newcommand{\policeTitreCouverture}[1]{\fontsize{18}{22}\selectfont\noindent {\bfseries #1}}
\newcommand{\policeTexteCouverture}[1]{\fontsize{12}{16}\selectfont\noindent #1}
\newcommand{\policeDateCouverture}[1]{\fontsize{14}{18}\selectfont\noindent #1}
\newcommand{\policeLegende}[1]{\fontsize{10}{12}\selectfont #1}
\newcommand{\policeMotsCles}[1]{\noindent #1}

\captionsetup{
  labelfont={bf,small},
  singlelinecheck=false,
  justification=centering
}

\counterwithin{figure}{section}
\counterwithin{table}{section}

% Définir le format de l'étiquette de la figure
\renewcommand{\thefigure}{\thesection.\arabic{figure}}

% Configuration du format d'étiquette de table
\renewcommand{\thetable}{\thesection.\arabic{table}}

\newcommand{\entete}[1]{%
  \policeEntete{#1}
}%

\newcommand{\titreCouverture}[1]{%
    \policeTitreCouverture{#1}\\
}%

\newcommand{\etudiants}[1]{%
    \policeTexteCouverture{\textbf{#1}}
}%

\newcommand{\donneeCouverture}[2]{%
    \policeTexteCouverture{#1: \textbf{#2}\\}
}%

\newcommand{\donneesCouverture}[1]{%
    #1
}%

\newcommand{\dateCouverture}[1]{%
    \policeDateCouverture{#1}
}%

\newcommand{\MotsCles}[1]{%
    \policeMotsCles{\textbf{Mots-clés: }\textit{#1}}
}%

\newcommand{\image}[4]{%
    \begin{figure}[H]
        \centering
        \includegraphics[width=#4\textwidth]{#1}
        \caption{\PoliceLegende{#2}}
        \label{fig:#3}
    \end{figure}
}

\newcommand{\ligneTableau}[1]{#1\\\hline}

\newcommand{\tableau}[4]{%
    \begin{table}[!htbp]
        \centering
        {\scriptsize
            \begin{tabular}{#3}
                \hline #4
            \end{tabular}
        }
        \caption{\PoliceLegende{#1}}
        \label{tab:#2}
    \end{table}
}

\newlist{actividades}{enumerate}{1}
\newlist{hitos}{enumerate}{1}
\newlist{subactividades}{enumerate}{2}

\setlist[actividades]{label=\makebox[1.75em][r]{\textbf{\scriptsize{\arabic*}.-}},leftmargin=2.5em, align=left}
\setlist[hitos]{label=\makebox[1.5em][r]{\textbf{{\roman*}}},leftmargin=2.5em, align=left}
\setlist[subactividades]{label=\textbf{{\scriptsize\arabic{actividadesi}.\arabic*}.-},resume=actividades,leftmargin=1.5em, align=left}

\newcommand{\activite}[2]{%
    {\scriptsize\bfseries(#1)}\hspace*{1em}{\small#2}
}

\newcommand{\jalon}[2]{%
    {\scriptsize\bfseries(#1)}\hspace*{1em}{\small#2}
}

\setlength{\parskip}{1em} % Définissez l'espace vertical entre les paragraphes sur 1em
\titlespacing{\section}{0pt}{0pt}{1em-\parskip}
\titlespacing{\subsection}{0pt}{0pt}{0.8em-\parskip}
\titlespacing{\subsubsection}{0pt}{0pt}{0.65em-\parskip}

\setlist{noitemsep} % Supprimer l'espace vertical entre les éléments des listes