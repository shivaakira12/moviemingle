function toggleMenu() {
    const navLinks = document.querySelector('.nav-links');
    navLinks.classList.toggle('show');
}

document.addEventListener('DOMContentLoaded', () => {
    const navItems = document.querySelectorAll('.nav-item');

    navItems.forEach(item => {
        item.addEventListener('click', () => {
            navItems.forEach(nav => nav.classList.remove('active'));
            item.classList.add('active');
        });
    });
});

function filterMovies() {
    const searchInput = document.getElementById('searchInput').value.toLowerCase();
    const movieContainer = document.getElementById('movieContainer');
    const movies = movieContainer.getElementsByClassName('movie-card');

    Array.from(movies).forEach((movie) => {
        const movieTitle = movie.querySelector('.movie-name').textContent.toLowerCase();
        const releaseYear = movie.querySelector('.release-year').textContent.toLowerCase();

        if (movieTitle.includes(searchInput) || releaseYear.includes(searchInput)) {
            movie.style.display = '';
        } else {
            movie.style.display = 'none';
        }
    });
}