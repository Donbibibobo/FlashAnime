<h1 align="center">FlashAnime</h1>

<!-- Start of Introduction -->
<div style="background-color: #f2f4f7; padding: 20px; border-radius: 10px; margin-bottom: 20px;">
    <h2 align="center" style="color: #0e75b6;">Unleash the power of anime to learn Japanese vocabulary.</h2>
    <p>Inspired by my love for anime, I developed FlashAnime to help fellow enthusiasts immerse in Japanese culture while learning the language.</p>
    <p>Language learning transcends mere knowledge. With FlashAnime, understanding every anime dialogue not only enhances your language skills but deepens your connection with the story. Our unique approach marries "entertainment" with "education," making learning everyday vocabulary an engaging experience.</p>
    <p>After each episode, assess your grasp with our quizzes. Add intriguing words to the "Vocabulary Collection" and revisit them via episode snippets, integrating learning into your daily routine.</p>
    <p>Embrace FlashAnime as your Japanese learning companion. Dive into captivating anime and join us on this linguistic journey!</p>
</div>

<!-- Start of Technical Stack -->
<div style="background-color: #e9ecef; padding: 20px; border-radius: 10px; margin-bottom: 20px;">
    <h3 align="center" style="color: #0e75b6;">🛠 Technical Stack</h3>
    <h4>Architecture Pattern:</h4>
    <ul>
        <li>MVVM (Model-View-ViewModel)</li>
    </ul>
    <h4>Networking:</h4>
    <ul>
        <li>RESTful API Integration</li>
        <li>Retrofit</li>
        <li>Moshi</li>
    </ul>
    <h4>Data Storage:</h4>
    <ul>
        <li>Room Database</li>
        <li>Firebase Cloud Firestore</li>
    </ul>
    <h4>Third-Party Libraries:</h4>
    <ul>
        <li><strong>Media Playback:</strong> <a href="https://github.com/PierfrancescoSoffritti/android-youtube-player">Youtube Player</a></li>
        <li><strong>UI Enhancements:</strong> <a href="https://github.com/yuyakaido/CardStackView">Card Stack View</a> for swipeable cards & <a href="https://github.com/lopspower/CircularProgressBar">Circular ProgressBar</a> for circular progressBar</li>
    </ul>
    <h4>Components:</h4>
    <ul>
        <li>Use Carousel with CarouselStrategy</li>
        <li>Material buttons</li>
    </ul>
    <h4>Testing:</h4>
    <ul>
        <li>Unit Tests</li>
        <li>UI Tests</li>
        <li>Mockito</li>
        <li>Robolectric</li>
    </ul>
</div>

<p align="left"> <img src="https://komarev.com/ghpvc/?username=donbibibobo&label=Profile%20views&color=0e75b6&style=flat" alt="donbibibobo" /> </p>

<p><strong>APK link:</strong> <a href="https://drive.google.com/drive/folders/1x7hM1m2GIN8Dn1GEDtTxY0M3WqRw9O6Y?usp=sharing">Download here</a></p>


<table>
  <tr>
    <td><img src="https://github.com/Donbibibobo/FlashAnime/assets/133195279/cd6b8838-96bd-4b89-b5af-d99d4b7d6694" alt="Home Page GIF" width="200"></td>
    <td>
      <h3 align="left">Home Page:</h3>
      <p><strong>Description:</strong> Seasonal anime updates presented on the home page.</p>
      <p><strong>Technical Implementation:</strong> Utilized a Carousel RecyclerView. Integrated Android's Palette library to dynamically determine primary image colors and update the background gradient accordingly.</p>
    </td>
  </tr>
  <tr>
    <td><img src="https://github.com/Donbibibobo/FlashAnime/assets/133195279/95ccc2ef-f78d-4ce3-8dc8-ffc09863d98f" alt="All Page GIF" width="200"></td>
    <td>
      <h3 align="left">All Page:</h3>
      <p><strong>Description:</strong> Displays all anime titles with a tag-based filter for user preferences.</p>
      <p><strong>Technical Implementation:</strong> Employed AppCompatDialogFragment to capture user-selected tags, filtering and displaying the relevant anime list accordingly.</p>
    </td>
  </tr>
  <tr>
    <td><img src="https://github.com/Donbibibobo/FlashAnime/assets/133195279/3b542653-2e84-4ede-85bc-5a7900934321" alt="Collected Page GIF" width="200"></td>
    <td>
      <h3 align="left">Collected Page:</h3>
      <p><strong>Description:</strong> View saved/favorite anime titles.</p>
      <p><strong>Technical Implementation:</strong> Leveraged Firebase to store user-favorited anime data.</p>
    </td>
  </tr>
  <tr>
    <td><img src="https://github.com/Donbibibobo/FlashAnime/assets/133195279/fd379d40-a5ce-41b9-9779-a9cd1b20fed5" alt="Detail Page GIF" width="200"></td>
    <td>
      <h3 align="left">Detail Page:</h3>
      <p><strong>Description:</strong> Watch anime and learn vocabulary simultaneously.</p>
      <p><strong>Technical Implementation:</strong></p>
      <ul>
          <li>Integrated Handler and Runnable for tracking vocabulary in videos, auto-scrolling the word list. Features include manual tracking disablement and clickable icons to navigate to specific word instances in the video.</li>
          <li>Word clicks launch a detailed dialog. Within this dialog, users can favorite the word, saving both its details and the corresponding video segment.</li>
      </ul>
    </td>
  </tr>
  <tr>
    <td><img src="https://github.com/Donbibibobo/FlashAnime/assets/133195279/6a2e6168-ac19-4e22-9afd-b8f9811afa5d" alt="Vocabulary Page GIF" width="200"></td>
    <td>
      <h3 align="left">Vocabulary Page:</h3>
      <p><strong>Description:</strong> Showcases vocabulary from each anime episode, facilitating self-review.</p>
      <p><strong>Technical Implementation:</strong> Incorporated a swipeable flashcard-style module for interactive vocabulary learning.</p>
    </td>
  </tr>
  <tr>
    <td><img src="https://github.com/Donbibibobo/FlashAnime/assets/133195279/23473e2a-6883-47ea-8d9c-890b46673c10" alt="Profile Page GIF" width="200"></td>
    <td>
      <h3 align="left">Profile Page:</h3>
      <p><strong>Description:</strong> Displays personal viewing history, anime favorites, and word favorites.</p>
      <p><strong>Technical Implementation:</strong> Under 'word favorites', clicking a word shows its details and replays the video segment using AppCompatDialogFragment for data presentation.</p>
    </td>
  </tr>
</table>


  </tr>
  <!-- Repeat for other sections... -->
</table>

