/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  images: {
    domains: [
      "localhost",
      "gamek.mediacdn.vn",
      "cdn.pixabay.com",
      "genk.mediacdn.vn",
      "s3.go2joy.vn",
      "cf.bstatic.com",
      "pix10.agoda.net",
      "chupanhnoithat.vn",
      "d2e5ushqwiltxm.cloudfront.net",
      "res.cloudinary.com",
    ],
  },
  async rewrites() {
    return [
      {
        source: "/api/cloudinary/:path*",
        destination: "https://api.cloudinary.com/:path*",
      },
    ];
  },
};

module.exports = nextConfig;
