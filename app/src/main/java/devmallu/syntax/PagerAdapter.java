package devmallu.syntax;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;


/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class PagerAdapter extends FragmentPagerAdapter {
    private String[] pageTitle = {"Find","Explore","Recent"};
    private FragmentManager mManager;
    PagerAdapter(FragmentManager fm) {
        super(fm);
        mManager =fm;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0)
            return FindFragment.newInstance();
        else
            return ExploreFragment.newInstance();
    }
    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle[position];
//        int[] imageResId={R.drawable.ic_find,R.drawable.ic_explore,R.drawable.ic_recent};
//        Drawable image = getDrawable(getItem(position).getContext(),imageResId[position]);
//        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
//        SpannableString sb = new SpannableString(" ");
//        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return sb;
    }

}
