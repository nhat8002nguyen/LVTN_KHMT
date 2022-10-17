/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  images: {
    domains: [
      "gamek.mediacdn.vn",
      "cdn.pixabay.com",
      "genk.mediacdn.vn",
      "s3.go2joy.vn",
      "cf.bstatic.com",
      "pix10.agoda.net",
    ],
  },
};

module.exports = nextConfig;
